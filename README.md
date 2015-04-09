ClojureScript Express Example
============================

This project shows how to use ClojureScript to build web servers using express
and node.js. You'll need:

    * leinengen http://leiningen.org/
    * node.js https://nodejs.org/
    * (optional) nodemon

Getting Started
---------------

Build the server:

    $ lein cljsbuild single server

Run the server:

    $ node out/cljs_express_example/example.js

Run the server and reload when files change:

    $ lein cljsbuild auto server
    $ nodemon out/cljs_express_example/example.js

Testing
-------

While developing, enable quick compilation of your project using
`lein auto-test`. Run tests using node:

    $ node test/bin/runner-none.js build/test build/test/test.js
