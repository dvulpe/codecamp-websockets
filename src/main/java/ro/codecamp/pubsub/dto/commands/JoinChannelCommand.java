package ro.codecamp.pubsub.dto.commands;

import org.atmosphere.cpr.Broadcaster;

public class JoinChannelCommand extends BaseCommand {

    @Override
    public void execute(Broadcaster broadcaster) {
        broadcaster.addAtmosphereResource(resource);
    }
}
