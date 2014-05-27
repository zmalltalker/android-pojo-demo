# Android subproject demo #

This subproject demonstrates how to isolate non-Android code into a
separate Gradle subproject, allowing you to develop and test your
non-Android code in isolation. Specifially, this setup lets you run
the unit tests using Android Studio's integrated test
runner. Furthermore, as your POJO code now has fewer depenencies you
will be able to run your tests quickly, for quick feedback during
development.

## Preparing your Android project ##

You will be setting up a Gradle subproject in your Android project,
where the non-Android (aka. POJO) code will reside. You can use this
repository as an example or starting point, so to get started you will
check out this repository into a subdirectory in your Android project.

### Check out the sub project ###

Assuming your Android project's root directory is
`~/Projects/myproject` you start out by

```
cd ~/Projects/myproject
git clone https://github.com/zmalltalker/android-pojo-demo.git
```

This checks out this repository into the `android-pojo-demo` directory
under your Android project root.

### Let Gradle know about the sub project ###

We now want to let Gradle know that this is a subproject, so we open
`settings.gradle` in your project repository and add

```gradle
include ':App', ':android-pojo-demo'
```

replacing `App` with whatever your Android subproject is named.

### Add the POJO project as a dependency ###

At this point, we want to add this subproject as a dependency in your
Android project, so you can use your POJO code from there. We do this
by adding a single line to the `build.gradle` file in your Android
subproject (note that this will be inside the `App` folder, not at the
project root):

```gradle
dependencies{
	…
	compile project(':android-pojo-demo')
    …
}
```

After updating/rebuilding your project, you will be able to use any
classes from this subproject in your Android code. The way this is
set up, (`compile project…`) your project depends on the `jar` Gradle
task in your subproject, so an up-to-date version of that will be
compiled and made available in your Android sub project.

## Running tests in Android studio ##

To run the unit tests from the POJO sub project inside Android Studio,
you need to set up a run configuration for that. Open the run
configurations dialog, click the Plus sign and select JUnit, and fill
out the page like this:

* Test kind: all in package
* Package: select the package containing the tests
  (`com.zmalltalker.tests`) in this project
* Use classpath of module: select the POJO subproject from the
  dropdown

Now, just run your test suite with `Ctrl-R` and watch that green bar!

## The subproject ##

This project is a very basic Gradle project using the `java` and
`application` plugins. This allows adding both production and test
code (as you can tell, I'm no Gradle expert), in familiar locations:

* Production code in `src/main/java`
* Test code in `src/test/java`

It also allows you to specify a main class to be run if you execute
the jar directly, but that's not used in this project.

## Use submodules ##

Rather than checking out a separate Git repository into your Android
project, you should rather use Git submodules to reference this
code. To do this:

```
git submodule add \
	https://github.com/zmalltalker/android-pojo-demo.git\
	android-pojo-demo
git submodule update --init
```
