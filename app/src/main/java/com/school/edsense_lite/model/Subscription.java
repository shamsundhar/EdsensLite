package com.school.edsense_lite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "table_subscription")
public class Subscription {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "subscription_id")
    private String subscriptionId;
    @ColumnInfo(name = "subscription_name")
    private String subscriptionName;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

}
