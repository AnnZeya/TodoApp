
//package app;
//
////import org.kohsuke.github.Gist;
//import org.kohsuke.github.*;
//
//import java.io.IOException;
//
//public class GistExporter {
//
//    private static final String GITHUB_TOKEN = "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";
//
//    public static String createSecretGist(String content, String fileName) throws IOException {
//        try {
//            GitHub github = new GitHubBuilder().withOAuthToken(GITHUB_TOKEN).build();
//            GHGist gist = github.createGist()
//                    .file(fileName, content)
//                    .secret(true)
//                    .create();
//            return gist.getHtmlUrl().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw e; // Re-throw the exception for handling at a higher level
//        }
//    }
//}


package app;

import org.kohsuke.github.GHGist;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class GistExporter {

    private static final String GITHUB_TOKEN = "";

    public static String createPublicGist(String content, String fileName) throws IOException {
        try {
            GitHub github = new GitHubBuilder().withOAuthToken(GITHUB_TOKEN).build();
            GHGist gist = github.createGist()
                    .public_(true) // Set the gist as public
                    .file(fileName, content)
                    .create();
            return gist.getHtmlUrl().toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception for handling at a higher level
        }
    }
}

