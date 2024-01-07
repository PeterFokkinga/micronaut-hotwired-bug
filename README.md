# micronaut-hotwired-bug
demonstrating that basic Hotwired Turbo support is broken in Micronaut 4.0.0 - 4.2.3

## Steps to reproduce
1. build and run the demo, default it uses Micronaut 4.2.3
2. click on a link in the "Episodes" frame, notice how only the "One Episode" frame refreshes (as expected)
3. click on the "edit" link, notice how only this frame refreshes, now showing an edit form
4. change something and click the "save" button: nothing happens but see the logs for exception

**now, change the Micronaut version to 3.10.3 and the plugin version to 3.7.10 and rebuild with --refresh-dependencies**

Repeat the steps above, but notice how step 4 now works as expected: it changes the contents of the "One Episode" to the view of the data you just edited.
