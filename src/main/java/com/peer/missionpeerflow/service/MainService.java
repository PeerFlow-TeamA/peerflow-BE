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

@Service
@RequiredArgsConstructor
public class MainService{
    private final MainRepository mainRepository;
    private final MainQuestionDTOMapper mainQuestionDTOMapper;

    public Page<MainQuestionDTO> getMainList(String category, String sort, int page, int size) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size, this.getQuestionPageSortClass(sort));
            Page<Question> questionList = this.mainRepository.findAllByCategory(category, pageRequest);
            return this.mainQuestionDTOMapper.toMainQuestionDTOPage(questionList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Not Found : getMainList");
        }
    }

    private Sort getQuestionPageSortClass(String sortKeyword) {
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
