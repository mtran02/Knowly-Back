package com.knowly.rest_api.controller.request;

public record NewCourseRequest(
        String name,
        Long price
) {}
