#!/bin/bash
export LANG=en_US.UTF-8
#This will cause the shell to exit immediately if a simple command exits with a nonzero exit value.
set -e

while [[ $# > 1 ]]
do
	sKey="$1"
	case $sKey in
		--technicalPause)
			technicalPause="$2"
			shift
			;;
		--implicitTimeWait)
			implicitTimeWait="$2"
			shift
			;;
		*)
			echo "bad option"
			exit 1
			;;
	esac
shift
done
sParam=$(echo "baseurl = ")
sUrl=$(cat /tmp/$sHost.txt)
sed -i  '$d'  src/test/resources/config.properties && echo $sParam $sUrl >> src/test/resources/config.properties
sed -i  '1d'  src/test/resources/config.properties && echo technicalPause=$technicalPause >> src/test/resources/config.properties
sed -i  '2d'  src/test/resources/config.properties && echo implicitTimeWait=$implicitTimeWait >> src/test/resources/config.properties
sSource=/tmp/BPMN.txt
chmod 777 /tmp/BPMN.txt

if [ ! -f $sSource ]; then 
echo "NOT FILE"
sed "11 a <package name=\"autoTests.TestSiute\"/>" testng_simple.xml > testng_new.xml
cp testng_new.xml testng.xml
else
echo "Add tests"
action () {
BPMN=${1/-/_}
if [ -e ./src/test/java/autoTests/TestSiute/${BPMN%.*}.java ]; then
echo $BPMN 
sed "6 a <class name=\"autoTests.TestSiute.${BPMN%.*}\"/>" testng_simple.xml > testng_new.xml
mv testng_new.xml testng_simple.xml
cp testng_simple.xml testng.xml
else 
mv testng_simple.xml testng.xml
fi
}
fi


while read sLine; do
 action  $sLine
 done < $sSource
rm /tmp/BPMN.txt
exit 0
