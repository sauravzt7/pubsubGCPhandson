package com.practice.lld.demopubsubproject.subscriber;

public interface ISubscriber {
    void onMessage(String projectId, String subId);
}
