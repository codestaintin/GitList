package com.example.isioyemohammed.gitlist.users;

import com.example.isioyemohammed.gitlist.model.GithubUsers;

import java.util.ArrayList;

/**
 * Created by isioyemohammed on 17/07/2018.
 * Gitlist
 */

public interface GithubUsersContract {
    /**
     * View interface.
     */
    interface MainView {
        /**
         * displayGithubUsers interface method.
         *
         * @param developerList - method parameter
         */
        void displayGithubUsers(ArrayList<GithubUsers> developerList);

        /**
         * void method for showing progress bar.
         */
        void showProgressBar();

        /**
         * void method for hiding progress bar.
         */
        void hideProgressBar();

        /**
         * void method for showing snackbar.
         *
         * @param message - String message
         */
        void showSnackBar(String message);

        /**
         * void method for hiding snackbar.
         */
        void hideSnackBack();

        /**
         * void method for hiding swipe to refresh widget.
         */
        void hideSwipeRefresh();
    }

    /**
     * Actions Interface.
     */
    interface Actions {
        /**
         * Get github users.
         *
         * @param status - Boolean status
         */
        void getGitHubUsers(Boolean status);
    }

    /**
     * Repository Interface.
     */
    interface Repository {
        /**
         * Query API method.
         * @param callback - callback parameter
         */
        void queryApi(Callback callback);

        /**
         * Callback Interface.
         */
        interface Callback {
            /**
             * onFinish callback method.
             * @param githubUsers - Github Users parameter
             */
            void onFinish(ArrayList<GithubUsers> githubUsers);

            /**
             * onError callback method.
             * @param throwable - throwable parameter
             */
            void onError(Throwable throwable);
        }

    }
}
