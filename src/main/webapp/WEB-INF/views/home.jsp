<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <link rel="stylesheet" media="all" href="/resources/style.css"/>
    <script type="text/javascript" src="/resources/jquery-1.7.1.min.js"></script>
    <script type="text/javascript">

        var WSApi = function (options) {
            function createConnection(url) {
                if ("WebSocket" in window) {
                    return new WebSocket(url);
                } else if ("MozWebSocket" in window) {
                    return new MozWebSocket(url);
                }
            }

            function prepareUrl(path) {
                return "ws://" + document.location.host + path;
            }

            function onMessage(message) {
                if (message.data !== "") {
                    (options.message)(JSON.parse(message.data));
                }
            }

            var ws = createConnection(prepareUrl(options.url));
            ws.onopen = options.open;
            ws.onmessage = onMessage;
            ws.onclose = options.onClose;

            return {
                "send":function (message) {
                    ws.send(JSON.stringify(message));
                },
                "close":function () {
                    ws.close();
                }
            }
        };

        $(function () {
            var numOfSlots = 10;
            var currentSlot = 1;

            var wsCallbackApi = {
                "receive":function (cmd) {
                    if (currentSlot == numOfSlots) {
                        currentSlot = 1;
                        setTimeout(function () {
                            var entries = $("#chat > li");
                            var entriesToSlice = entries.size() > numOfSlots * 3 ? numOfSlots : 0;
                            entries.slice(0, entriesToSlice).remove();
                        }, 100);
                    } else {
                        currentSlot++;
                    }
                    $("<li></li>").text(cmd.payload)
                            .addClass(cmd.party.toLowerCase())
                            .css("left", currentSlot * 30 + "px")
                            .appendTo("#chat");
                }
            };

            var browserName;
            (function () {
                if ($.browser.webkit && /chrome/.test(navigator.userAgent.toLowerCase())) {
                    browserName = "Chrome";
                } else if ($.browser.webkit) {
                    browserName = "Safari";
                } else if ($.browser.mozilla) {
                    browserName = "Firefox";
                }
            })();

            var ws;

            $("#open-connection").click(function () {
                ws = new WSApi({"url":"/websockets",
                    "open":function () {
                        ws.send({"type":"subscribe",
                            "channel":"codecamp"});
                    },
                    "message":function (cmd) {
                        wsCallbackApi[cmd.type](cmd);
                    }});
            });

            (function ($) {
                var interval;
                var publish = function () {
                    ws.send({"type":"broadcastMessage",
                        "channel":"codecamp",
                        "payload":browserName + ": hello!",
                        "party":browserName})
                };
                $("#toggle-publishing").click(function () {
                            if (interval == null) {
                                publish();
                                interval = setInterval(publish, 1500);
                            }
                            else {
                                clearInterval(interval);
                                interval = null;
                            }
                        }
                );
            })(jQuery);

            window.onunload = function () {
                ws.close();
            }
        });
    </script>
</head>
<body>
<div id="container">
    <ul id="menu">
        <li>
            <input type="button" id="open-connection" value="Open Connection"/>
        </li>
        <li>
            <input type="button" id="toggle-publishing" value="Publish"/>
        </li>
    </ul>
    <ul id="chat"></ul>
</div>
</body>
</html>
