package household;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface HouseholdMessageChannels {

    String DELETION_INPUT = "deletionInput";
    String CREATION_INPUT = "creationInput";

    @Input
    SubscribableChannel deletionInput();

    @Output
    MessageChannel deletionOutput();

    @Input
    SubscribableChannel creationInput();

    @Output
    MessageChannel creationOutput();

}
