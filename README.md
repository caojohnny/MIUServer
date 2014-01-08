The server implementation for [Mine In Unity](https://github.com/mattrick16/Mine-In-Unity)

Maintained by AgentTroll and TheKomputerKing.

Builds/Status coming soon, while we're at it, check out [this](http://mineinunity.tk/forums/viewtopic.php?f=4&t=16)

====

**Builds**

- [![Build Status](https://travis-ci.org/AgentTroll/MIUServer.png?branch=master)](https://travis-ci.org/AgentTroll/MIUServer)

- Download now, direct link: https://github.com/AgentTroll/MIUServerBuilds/blob/master/com/gmail/woodyc40/miuserver/MIUServer/1.0/MIUServer-1.0.jar?raw=true

====

**Protocol**

MAJOR REWORK COMING SOON.

- Your listener classes must extend the event class
- Override the onSend() method to execute your code
- Register by calling

```java

EventHandler.register(new ClassName());
```
or use the EventAdapter feature like so:
```java

EventAdapter adapter = new EventAdapter(PacketType.SEND, new CodeExecutor<PacketSend>() {
    @Override
    public void runCode(PacketSend packet) {
        // Do stuff
    }
});
```
Note the PacketSend's, they must be the same throughout, and the PacketType must be the same as the packet type.
An IllegalArgumentException will be thrown for a packet type that does not match the packet.

You do not need to register the EventAdapter, it is automatically handled.
