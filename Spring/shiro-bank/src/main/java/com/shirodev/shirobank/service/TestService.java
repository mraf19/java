package com.shirodev.shirobank.service;


import com.shirodev.shirobank.model.dto.request.TestResponse;
import com.shirodev.shirobank.model.dto.response.TestRequest;

import java.util.List;

public interface TestService {
    TestResponse createTest(TestRequest request);
    List<TestResponse> getAllTests();
    TestResponse getTestById(String id);
    TestResponse updateTest(TestRequest request);
    void deleteTest(String id);
}
