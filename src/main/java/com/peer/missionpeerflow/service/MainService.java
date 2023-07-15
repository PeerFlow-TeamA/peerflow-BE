package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.response.MainQuestionDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import com.peer.missionpeerflow.repository.MainRepository;
import com.peer.missionpeerflow.dto.mapper.MainQuestionDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MainService{
    private final MainRepository mainRepository;
    private final MainQuestionDTOMapper mainQuestionDTOMapper;

    @Transactional
    public Page<MainQuestionDTO> getMainList(String category, String sort, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, this.getQuestionPageSortClassByRequestSort(sort));
        Page<Question> questionList;
        if (category.equals("all")) {
            questionList = this.mainRepository.findAll(pageRequest);
        } else {
            questionList = this.mainRepository.findAllByCategory(category, pageRequest);
        }
        return this.mainQuestionDTOMapper.toMainQuestionDTOPage(questionList);
    }

    @Transactional
    protected Sort getQuestionPageSortClassByRequestSort(String sortKeyword) {
        switch (sortKeyword) {
            case "latest":
                return Sort.by("createdAt").descending();
            case "views":
                return Sort.by("views").descending();
            case "recommend":
                return Sort.by("recommend").descending();
            default:
                return Sort.by("id").descending();
        }
    }
}
