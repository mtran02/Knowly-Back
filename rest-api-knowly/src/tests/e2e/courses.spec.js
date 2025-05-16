const { defineConfig } = require("@playwright/test");

module.exports = defineConfig({
  testDir: "./src/tests/e2e",
  use: {
    baseURL: "http://localhost:8081",
    headless: false,
    viewport: { width: 1280, height: 720 },
    screenshot: "only-on-failure",
    video: "retain-on-failure",
    trace: "on-first-retry",
  },
  reporter: [["html"], ["list"]],
  projects: [
    {
      name: "Chrome",
      use: { browserName: "chromium" },
    },
  ],
});
