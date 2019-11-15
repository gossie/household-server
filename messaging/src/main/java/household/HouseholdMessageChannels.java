package household;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface HouseholdMessageChannels {

    String DELETION_PRODUCER = "deletionProducer";
    String CREATION_PRODUCER = "creationProducer";

    @Input
    SubscribableChannel deletionProducer();

    @Output
    MessageChannel deletionConsumer();

    @Input
    SubscribableChannel creationProducer();

    @Output
    MessageChannel creationConsumer();

}
