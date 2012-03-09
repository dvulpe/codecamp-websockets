package ro.codecamp.pubsub.dto.commands;

import org.atmosphere.cpr.Broadcaster;

public class UnsubscribeCommand extends BaseCommand {

    @Override
    public void execute(Broadcaster broadcaster) {
        broadcaster.removeAtmosphereResource(resource);
    }
}
