package com.school.edsense_lite.notes;

import java.util.List;

public class GetUsersForNotesResponse {
    private List<Response> response = null;
    private Boolean isSuccess;
    private Integer errorCode;
    private String errorMessage;
    private Boolean isUserActive;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }
    public class Response {

        private Integer userIdentityId;
        private Object landingURL;
        private Object userRoles;
        private Boolean isStudent;
        private Boolean isAdmin;
        private Boolean isSuspended;
        private String userId;
        private Object primaryOrg;
        private Object primaryOrgName;
        private Boolean isApproved;
        private Object hasResetPasswordDone;
        private String userName;
        private String displayName;
        private Object bloodGroup;
        private Object dateofJoin;
        private String firstName;
        private Integer points;
        private Object title;
        private Object type;
        private String lastName;
        private Object email;
        private Object userScope;
        private Object additionalInfo;
        private Object password;
        private Boolean isImpersonated;
        private Object secretKey;
        private Object userKey;
        private Object dateOfBirth;
        private Object phoneNumber;
        private Object phoneNumber1;
        private Object phoneNumber2;
        private Object phoneNumber3;
        private Object aadharNumber;
        private Object locationCoordinatesAddress;
        private Object locationCoordinates;
        private Object locationName;
        private Object line1;
        private Object line2;
        private Object line3;
        private Object city;
        private Object state;
        private Object country;
        private Integer relationId;
        private Object relationName;
        private Object imageFileFullPath;
        private Object subscriptionId;
        private Object subscriptionName;
        private Object portalName;
        private Object lastLoginDate;
        private Object image;
        private Integer status;
        private Integer gender;
        private Object tags;
        private Object tagsJson;
        private Integer tagId;
        private List<Object> userProperties = null;
        private List<Object> organizationAssociationWithUser = null;
        private List<Object> userRelation = null;
        private List<Object> relationMasterData = null;
        private List<Object> userSubscriptions = null;
        private List<Object> supportTickets = null;
        private Object phoneNumbers;
        private Object createdDate;
        private Object updatedDate;
        private Boolean isLockout;
        private Boolean isActive;
        private Boolean isLoginPrerequisiteCompleted;
        private String roleName;
        private Object roles;
        private String grade;
        private Object gradeName;
        private Integer studentNotesCount;
        private Object userExperience;
        private Object boardId;
        private Boolean isPinned;

        public Integer getUserIdentityId() {
            return userIdentityId;
        }

        public void setUserIdentityId(Integer userIdentityId) {
            this.userIdentityId = userIdentityId;
        }

        public Object getLandingURL() {
            return landingURL;
        }

        public void setLandingURL(Object landingURL) {
            this.landingURL = landingURL;
        }

        public Object getUserRoles() {
            return userRoles;
        }

        public void setUserRoles(Object userRoles) {
            this.userRoles = userRoles;
        }

        public Boolean getIsStudent() {
            return isStudent;
        }

        public void setIsStudent(Boolean isStudent) {
            this.isStudent = isStudent;
        }

        public Boolean getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(Boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

        public Boolean getIsSuspended() {
            return isSuspended;
        }

        public void setIsSuspended(Boolean isSuspended) {
            this.isSuspended = isSuspended;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getPrimaryOrg() {
            return primaryOrg;
        }

        public void setPrimaryOrg(Object primaryOrg) {
            this.primaryOrg = primaryOrg;
        }

        public Object getPrimaryOrgName() {
            return primaryOrgName;
        }

        public void setPrimaryOrgName(Object primaryOrgName) {
            this.primaryOrgName = primaryOrgName;
        }

        public Boolean getIsApproved() {
            return isApproved;
        }

        public void setIsApproved(Boolean isApproved) {
            this.isApproved = isApproved;
        }

        public Object getHasResetPasswordDone() {
            return hasResetPasswordDone;
        }

        public void setHasResetPasswordDone(Object hasResetPasswordDone) {
            this.hasResetPasswordDone = hasResetPasswordDone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Object getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(Object bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public Object getDateofJoin() {
            return dateofJoin;
        }

        public void setDateofJoin(Object dateofJoin) {
            this.dateofJoin = dateofJoin;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getUserScope() {
            return userScope;
        }

        public void setUserScope(Object userScope) {
            this.userScope = userScope;
        }

        public Object getAdditionalInfo() {
            return additionalInfo;
        }

        public void setAdditionalInfo(Object additionalInfo) {
            this.additionalInfo = additionalInfo;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Boolean getIsImpersonated() {
            return isImpersonated;
        }

        public void setIsImpersonated(Boolean isImpersonated) {
            this.isImpersonated = isImpersonated;
        }

        public Object getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(Object secretKey) {
            this.secretKey = secretKey;
        }

        public Object getUserKey() {
            return userKey;
        }

        public void setUserKey(Object userKey) {
            this.userKey = userKey;
        }

        public Object getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Object dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getPhoneNumber1() {
            return phoneNumber1;
        }

        public void setPhoneNumber1(Object phoneNumber1) {
            this.phoneNumber1 = phoneNumber1;
        }

        public Object getPhoneNumber2() {
            return phoneNumber2;
        }

        public void setPhoneNumber2(Object phoneNumber2) {
            this.phoneNumber2 = phoneNumber2;
        }

        public Object getPhoneNumber3() {
            return phoneNumber3;
        }

        public void setPhoneNumber3(Object phoneNumber3) {
            this.phoneNumber3 = phoneNumber3;
        }

        public Object getAadharNumber() {
            return aadharNumber;
        }

        public void setAadharNumber(Object aadharNumber) {
            this.aadharNumber = aadharNumber;
        }

        public Object getLocationCoordinatesAddress() {
            return locationCoordinatesAddress;
        }

        public void setLocationCoordinatesAddress(Object locationCoordinatesAddress) {
            this.locationCoordinatesAddress = locationCoordinatesAddress;
        }

        public Object getLocationCoordinates() {
            return locationCoordinates;
        }

        public void setLocationCoordinates(Object locationCoordinates) {
            this.locationCoordinates = locationCoordinates;
        }

        public Object getLocationName() {
            return locationName;
        }

        public void setLocationName(Object locationName) {
            this.locationName = locationName;
        }

        public Object getLine1() {
            return line1;
        }

        public void setLine1(Object line1) {
            this.line1 = line1;
        }

        public Object getLine2() {
            return line2;
        }

        public void setLine2(Object line2) {
            this.line2 = line2;
        }

        public Object getLine3() {
            return line3;
        }

        public void setLine3(Object line3) {
            this.line3 = line3;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Integer getRelationId() {
            return relationId;
        }

        public void setRelationId(Integer relationId) {
            this.relationId = relationId;
        }

        public Object getRelationName() {
            return relationName;
        }

        public void setRelationName(Object relationName) {
            this.relationName = relationName;
        }

        public Object getImageFileFullPath() {
            return imageFileFullPath;
        }

        public void setImageFileFullPath(Object imageFileFullPath) {
            this.imageFileFullPath = imageFileFullPath;
        }

        public Object getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(Object subscriptionId) {
            this.subscriptionId = subscriptionId;
        }

        public Object getSubscriptionName() {
            return subscriptionName;
        }

        public void setSubscriptionName(Object subscriptionName) {
            this.subscriptionName = subscriptionName;
        }

        public Object getPortalName() {
            return portalName;
        }

        public void setPortalName(Object portalName) {
            this.portalName = portalName;
        }

        public Object getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(Object lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Object getTagsJson() {
            return tagsJson;
        }

        public void setTagsJson(Object tagsJson) {
            this.tagsJson = tagsJson;
        }

        public Integer getTagId() {
            return tagId;
        }

        public void setTagId(Integer tagId) {
            this.tagId = tagId;
        }

        public List<Object> getUserProperties() {
            return userProperties;
        }

        public void setUserProperties(List<Object> userProperties) {
            this.userProperties = userProperties;
        }

        public List<Object> getOrganizationAssociationWithUser() {
            return organizationAssociationWithUser;
        }

        public void setOrganizationAssociationWithUser(List<Object> organizationAssociationWithUser) {
            this.organizationAssociationWithUser = organizationAssociationWithUser;
        }

        public List<Object> getUserRelation() {
            return userRelation;
        }

        public void setUserRelation(List<Object> userRelation) {
            this.userRelation = userRelation;
        }

        public List<Object> getRelationMasterData() {
            return relationMasterData;
        }

        public void setRelationMasterData(List<Object> relationMasterData) {
            this.relationMasterData = relationMasterData;
        }

        public List<Object> getUserSubscriptions() {
            return userSubscriptions;
        }

        public void setUserSubscriptions(List<Object> userSubscriptions) {
            this.userSubscriptions = userSubscriptions;
        }

        public List<Object> getSupportTickets() {
            return supportTickets;
        }

        public void setSupportTickets(List<Object> supportTickets) {
            this.supportTickets = supportTickets;
        }

        public Object getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(Object phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
            this.createdDate = createdDate;
        }

        public Object getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(Object updatedDate) {
            this.updatedDate = updatedDate;
        }

        public Boolean getIsLockout() {
            return isLockout;
        }

        public void setIsLockout(Boolean isLockout) {
            this.isLockout = isLockout;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Boolean getIsLoginPrerequisiteCompleted() {
            return isLoginPrerequisiteCompleted;
        }

        public void setIsLoginPrerequisiteCompleted(Boolean isLoginPrerequisiteCompleted) {
            this.isLoginPrerequisiteCompleted = isLoginPrerequisiteCompleted;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public Object getRoles() {
            return roles;
        }

        public void setRoles(Object roles) {
            this.roles = roles;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public Object getGradeName() {
            return gradeName;
        }

        public void setGradeName(Object gradeName) {
            this.gradeName = gradeName;
        }

        public Integer getStudentNotesCount() {
            return studentNotesCount;
        }

        public void setStudentNotesCount(Integer studentNotesCount) {
            this.studentNotesCount = studentNotesCount;
        }

        public Object getUserExperience() {
            return userExperience;
        }

        public void setUserExperience(Object userExperience) {
            this.userExperience = userExperience;
        }

        public Object getBoardId() {
            return boardId;
        }

        public void setBoardId(Object boardId) {
            this.boardId = boardId;
        }

        public Boolean getIsPinned() {
            return isPinned;
        }

        public void setIsPinned(Boolean isPinned) {
            this.isPinned = isPinned;
        }

    }
}
