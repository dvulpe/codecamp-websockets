package ro.codecamp.pubsub.dto.commands;

import org.atmosphere.cpr.Broadcaster;

public class EmptyCommand extends BaseCommand {
    @Override
    public void execute(Broadcaster broadcaster) {
        // nothing needed
    }
}
