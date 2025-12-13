# GitHub Setup Instructions

Your repository is ready to push to GitHub! Follow these steps:

## Step 1: Create a GitHub Repository

1. Go to [GitHub.com](https://github.com) and sign in
2. Click the **"+"** icon in the top right corner
3. Select **"New repository"**
4. Fill in the details:
   - **Repository name**: `plugins.splash` (or `splash-plugin`)
   - **Description**: "Minecraft PaperMC plugin that creates block splash effects when players land"
   - **Visibility**: Choose Public or Private
   - **DO NOT** initialize with README, .gitignore, or license (we already have these)
5. Click **"Create repository"**

## Step 2: Add Remote and Push

After creating the repository, GitHub will show you commands. Use these:

```bash
# Add the remote (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/plugins.splash.git

# Or if you prefer SSH:
git remote add origin git@github.com:YOUR_USERNAME/plugins.splash.git

# Push to GitHub
git branch -M main
git push -u origin main
```

## Alternative: Using GitHub CLI

If you have GitHub CLI installed:

```bash
# Create repo and push in one command
gh repo create plugins.splash --public --source=. --remote=origin --push
```

## Verify

After pushing, visit your repository on GitHub to verify all files are there:
- `https://github.com/YOUR_USERNAME/plugins.splash`

## Next Steps

- Add a license file (MIT, GPL, etc.) if desired
- Set up GitHub Actions for CI/CD if needed
- Add topics/tags to your repository for discoverability

