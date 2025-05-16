const { test, expect } = require("@playwright/test");

test.describe("Course API Tests", () => {
  const apiUrl = "http://localhost:8081/api";

  test("should get courses list", async ({ request }) => {
    const response = await request.get(`${apiUrl}/courses`);
    expect(response.ok()).toBeTruthy();
    const courses = await response.json();
    expect(Array.isArray(courses)).toBeTruthy();
  });

  test("should create a new course", async ({ request }) => {
    const course = {
      title: "Test Course",
      description: "Test Description",
    };

    const response = await request.post(`${apiUrl}/courses`, {
      data: course,
    });
    expect(response.status()).toBe(201);
    const createdCourse = await response.json();
    expect(createdCourse.title).toBe(course.title);
  });
});
