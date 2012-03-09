tell application "Firefox"
	activate
	set bounds of window 1 to {1708, 322, 2188, 758}
	delay 1
end tell
tell application "System Events"
	keystroke "l" using {command down}
	delay 1
	keystroke "http://localhost:8080/"
	key code 36
end tell

tell application "Safari"
	activate
	if windows is {} then reopen
	make new document with properties {URL:"http://localhost:8080/"}
	set the bounds of the first window to {1444, 2, 1945, 427}
end tell

tell application "Google Chrome"
	activate
	if windows is {} then reopen
	set URL of active tab of window 1 to "http://localhost:8080/"
	set the bounds of the first window to {1947, 2, 2461, 428}
end tell