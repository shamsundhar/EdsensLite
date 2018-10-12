package com.school.edsense_lite.login;

import com.school.edsense_lite.model.BaseResponse;
import com.school.edsense_lite.model.Subscription;

import java.util.List;

/**
 * Created by shyam on 4/10/2018.
 */

public class LoginResponse {
    private Response response;
    private String isSuccess;
    private String errorMessage;
    private String errorCode;
    private String isUserActive;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(String isUserActive) {
        this.isUserActive = isUserActive;
    }




    public class Response{
        private String bearerToken;
        private String isUserValid;
        private String landingPage;
        private String userScope;
        private String hasResetPasswordDone;
        private String isImpersonated;
        //private String isUserActive;
        private List<Subscription> userSubscriptions;

        public String getBearerToken() {
            return bearerToken;
        }

        public void setBearerToken(String bearerToken) {
            this.bearerToken = bearerToken;
        }

        public String getIsUserValid() {
            return isUserValid;
        }

        public void setIsUserValid(String isUserValid) {
            this.isUserValid = isUserValid;
        }

        public String getLandingPage() {
            return landingPage;
        }

        public void setLandingPage(String landingPage) {
            this.landingPage = landingPage;
        }

        public String getUserScope() {
            return userScope;
        }

        public void setUserScope(String userScope) {
            this.userScope = userScope;
        }

        public String getHasResetPasswordDone() {
            return hasResetPasswordDone;
        }

        public void setHasResetPasswordDone(String hasResetPasswordDone) {
            this.hasResetPasswordDone = hasResetPasswordDone;
        }

        public String getIsImpersonated() {
            return isImpersonated;
        }

        public void setIsImpersonated(String isImpersonated) {
            this.isImpersonated = isImpersonated;
        }
        public List<Subscription> getUserSubscriptions() {
            return userSubscriptions;
        }

        public void setUserSubscriptions(List<Subscription> userSubscriptions) {
            this.userSubscriptions = userSubscriptions;
        }
        //    @Override
//    public String getIsUserActive() {
//        return isUserActive;
//    }
//
//    @Override
//    public void setIsUserActive(String isUserActive) {
//        this.isUserActive = isUserActive;
//    }
    }

}
