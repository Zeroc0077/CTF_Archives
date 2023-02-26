#!/bin/sh
# Add your startup script

echo $FLAG > flag && export FLAG=not && FLAG=not

# DO NOT DELETE

/etc/init.d/xinetd start;
sleep infinity;
