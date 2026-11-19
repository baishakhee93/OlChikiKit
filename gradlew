#!/usr/bin/env sh

DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'
app_name="Gradle"
app_home=$(dirname "$(readlink -f "$0" 2>/dev/null || perl -le 'print $RealBin foreach ($0)' 2>/dev/null || echo "$0")")

if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

CLASSPATH=$app_home/gradle/wrapper/gradle-wrapper.jar

exec "$JAVACMD" $DEFAULT_JVM_OPTS $GRADLE_OPTS "-Dorg.gradle.appname=$app_name" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
