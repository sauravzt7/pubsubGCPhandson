package com.practice.lld.demopubsubproject.messagecreators;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class MessageCreator {

    Lorem lorem = LoremIpsum.getInstance();

    public String createARandomMessage(){
        String randomMessage = lorem.getParagraphs(2, 4);
        return randomMessage;
    }

}
