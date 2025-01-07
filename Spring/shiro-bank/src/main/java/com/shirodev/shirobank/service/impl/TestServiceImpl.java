package com.shirodev.shirobank.service.impl;

import com.shirodev.shirobank.model.dto.request.TestResponse;
import com.shirodev.shirobank.model.dto.response.TestRequest;
import com.shirodev.shirobank.model.entity.Test;
import com.shirodev.shirobank.repository.TestRepository;
import com.shirodev.shirobank.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    @Override
    public TestResponse createTest(TestRequest request) {
        Test test = new Test();
        test.setName(request.getName());
        testRepository.save(test);
        return toTestResponse(test);
    }

    @Override
    public List<TestResponse> getAllTests() {
        return toListTestResponse(testRepository.findAll());
    }

    @Override
    public TestResponse getTestById(String id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TEST MOT FOUND"));
        return toTestResponse(test);
    }

    @Override
    public TestResponse updateTest(TestRequest request) {
        Test test = testRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TEST MOT FOUND"));
        test.setName(request.getName());
        testRepository.save(test);
        return toTestResponse(test);
    }

    @Override
    public void deleteTest(String id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TEST MOT FOUND"));
        testRepository.delete(test);
    }

    private TestResponse toTestResponse(Test test){
        return TestResponse
                .builder()
                .id(test.getId())
                .name(test.getName())
                .createdAt(test.getCreatedAt())
                .updatedAt(test.getUpdatedAt())
                .build();
    }

    private List<TestResponse> toListTestResponse(List<Test> tests){
        return tests.stream().map(this::toTestResponse).toList();
    }
}
