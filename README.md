# Azure DevOps Integration with Quokka Q-Mast: A Comprehensive Walkthrough

Welcome to our step-by-step guide on how to integrate the **Quokka Q-Mast** plugin into your Azure DevOps pipeline. This walkthrough will help you get set up, configure the necessary settings, and run your mobile application security analysis seamlessly.

## Why Use Quokka Q-Mast in Azure DevOps?

Security is critical when developing mobile applications. The **Quokka Q-Mast** plugin for Azure DevOps simplifies mobile app security testing, allowing you to embed security checks directly into your DevOps pipeline. This ensures that every build is vetted, reducing the risk of vulnerabilities making their way to production.

---

## Prerequisites

Before you begin, make sure:

1. You have an existing Azure DevOps pipeline where a compiled binary is accessible, or you create a new one that outputs a compiled binary artifact (.ipa for iOS or .apk for Android).
2. The **Quokka Q-Mast** extension is added to your Azure DevOps environment via the [Azure DevOps Marketplace](https://marketplace.visualstudio.com/items?itemName=Kryptowire-Inc.kryptowire-mobile-app-security-testing).

---

## Configuration Steps

### 1. Edit Your Pipeline

Start by selecting the pipeline where you'd like to integrate Quokka Q-Mast. Click **Edit** on the chosen pipeline.

### 2. Add the Quokka Task

In the Tasks sidebar, search for and select **Quokka**. This will add the Quokka Q-Mast task to your pipeline.

### 3. Configure the Quokka Q-Mast Task

Once the task is added, you'll need to input the required configuration fields:

- **API URL**: The Quokka API endpoint (pre-configured, do not change this).
- **File Path**: The path to the mobile app binary (.ipa/.apk) that you want to upload for security analysis.
- **API Key**: Your Quokka API Key, which you can find in the **Q-Mast platform** under **Settings**.
- **Platform**: Specify whether the app is for **iOS** or **Android**.
- **Result Directory**: The directory where the analysis results will be stored (auto-populated by default).
- **External ID (Optional)**: Any external ID you’d like to associate with this app submission.
- **Sub Group IDs (Optional)**: If the mobile app belongs to specific subgroups, you can define them here.

### 4. Advanced Settings

For further customization, click **Advanced Settings** and configure:

- **Score Threshold**: Set a threshold to fail the build if the threat score exceeds the provided value.
- **Max Analysis Time**: Define the maximum time (in minutes) that the pipeline should wait for the security analysis to complete. If the analysis doesn't finish in this time, the build will fail. For non-blocking builds, set this to `0` (the analysis will run in the background, and results will be available later in the Q-Mast portal).

### 5. Run the Pipeline

Once configured, run the pipeline! If you set the **Max Analysis Time** to `0`, the build will proceed without waiting for the analysis results. Analysis can take up to 2 hours to complete, depending on the size and complexity of the application.

---

## Best Practices for Using Quokka Q-Mast in Azure DevOps

- **Set Sensible Score Thresholds**: Start with a moderate threshold to avoid build failures due to minor issues. Gradually tighten the threshold as your team becomes more familiar with the analysis reports.
- **Use Non-blocking Builds in Early Stages**: When first integrating security checks into your pipeline, it's often better to run non-blocking builds (setting **Max Analysis Time** to `0`). This allows the team to review security results without affecting build times.
- **Monitor Analysis Results Regularly**: Even if builds pass, regularly review the Q-Mast analysis results for early detection of security vulnerabilities that could accumulate over time.

---

## Troubleshooting

- **Invalid API Key**: Ensure that the API Key is copied directly from the Q-Mast platform under **Settings**. Double-check for any extra spaces.
- **Build Failed Due to Analysis Timeout**: If your build fails because the analysis took too long, consider increasing the **Max Analysis Time** in the Advanced Settings or set it to `0` to allow non-blocking builds.
- **Pipeline Not Finding the Mobile Binary**: Make sure the **File Path** points to the correct directory where the .ipa or .apk is generated.

---

## Additional Resources

- [Quokka Q-Mast Documentation](#)
- [Azure DevOps Pipelines Documentation](https://learn.microsoft.com/en-us/azure/devops/pipelines/)
- [Mobile Application Security Best Practices](#)

---

## Need Help?

If you run into any issues or have questions, feel free to contact the Quokka support team at [support@quokka.io](mailto:support@quokka.io) or visit our [support page](#).

---

## Conclusion

Integrating Quokka Q-Mast into your Azure DevOps pipeline ensures robust security testing for your mobile applications, automating a critical aspect of the DevOps process. Whether you're looking for real-time build blocking or post-build analysis, this plugin has you covered.

By following this guide, you should now have the Quokka Q-Mast plugin up and running in your Azure DevOps environment, providing peace of mind for your mobile application security.