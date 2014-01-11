The server implementation for [Mine In Unity](https://github.com/mattrick16/Mine-In-Unity)

Maintained by AgentTroll and TheKomputerKing.

Builds/Status coming soon, while we're at it, check out [this](http://mineinunity.tk/forums/viewtopic.php?f=4&t=16)

====

**Builds**

- [![Build Status](https://travis-ci.org/AgentTroll/MIUServer.png?branch=master)](https://travis-ci.org/AgentTroll/MIUServer)

- Download now, direct link: https://github.com/AgentTroll/MIUServerBuilds/blob/master/com/gmail/woodyc40/miuserver/MIUServer/1.0/MIUServer-1.0.jar?raw=true

====

**Protocol**

- Your listener classes must extend the event class
- Override the onEvent() method to execute your code
- Register by calling

```java

EventHandler.getInstance().register(new ClassName());
```
or use the EventAdapter feature like so:
```java

EventAdapter<PacketSend> adapter = new EventAdapter<>(new CodeExecutor<Event<PacketSend>>(new PacketSend(/* Data here */)) {
    @Override
    public void runCode(Event<PacketSend> e) {
        e.onEvent(e);
    }
});
```

You do not need to register the EventAdapter, it is automatically handled.
