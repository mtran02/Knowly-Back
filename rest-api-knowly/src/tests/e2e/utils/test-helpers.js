exports.createTestCourse = async (request, course = null) => {
  const defaultCourse = course || {
    title: "Test Course",
    description: "Test Description",
  };

  const response = await request.post(`http://localhost:8081/api/courses`, {
    data: defaultCourse,
  });

  return response.json();
};
