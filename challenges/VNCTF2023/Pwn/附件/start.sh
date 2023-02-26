#!/bin/sh
echo $FLAG > /home/ctf/flag && export FLAG=not && FLAG=not

/etc/init.d/xinetd start
sleep infinity
