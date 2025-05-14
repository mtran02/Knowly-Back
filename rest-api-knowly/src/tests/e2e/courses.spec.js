module.exports = {
  testDir: "./tests",
  use: {
    baseURL: "http://localhost:8081",
    trace: "on-first-retry",
  },
};

const { test, expect } = require("@playwright/test");

test("should display courses list", async ({ page }) => {
  await page.goto("/courses");
  const response = await page.waitForResponse("**/courses");
  expect(response.status()).toBe(200);
});
