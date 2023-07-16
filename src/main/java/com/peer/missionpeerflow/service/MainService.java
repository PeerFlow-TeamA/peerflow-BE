package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.util.Category;
import lombok.RequiredArgsConstructor;
import com.peer.missionpeerflow.repository.MainRepository;
import com.peer.missionpeerflow.dto.mapper.MainQuestionDTOMapper;
import org.hibernate.QueryParameterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService{
    private final MainRepository mainRepository;
    private final MainQuestionDTOMapper mainQuestionDTOMapper;

    public Page<MainQuestionDTO> getMainList(String category, String sort, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, this.getQuestionPageSortClassByRequestSort(sort));
        Page<Question> questionList;
        if (category.equals("all")) {
            questionList = this.mainRepository.findAll(pageRequest);
        } else {
            Category categoryEnum = Category.ofType(category);
            questionList = this.mainRepository.findAllByCategory(categoryEnum, pageRequest);
        }
        return this.mainQuestionDTOMapper.toMainQuestionDTOPage(questionList);
    }

    public Page<MainQuestionDTO> getSearchList(String title, String sort, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, this.getQuestionPageSortClassByRequestSort(sort));
        Page<Question> questionList = this.mainRepository.findAllByTitleContaining(title, pageRequest);
        return this.mainQuestionDTOMapper.toMainQuestionDTOPage(questionList);
    }

    private Sort getQuestionPageSortClassByRequestSort(String sortKeyword) {
        switch (sortKeyword) {
            case "latest":
                return Sort.by("createdAt").descending();
            case "views":
                return Sort.by("view").descending();
            case "recommends":
                return Sort.by("recommend").descending();
            default:
                throw new QueryParameterException("sort standard incorrected");
        }
    }
}
