#!/bin/sh
./qemu-system-x86_64 \
    -m 64M --nographic \
    -initrd ./rootfs.cpio \
    -nographic \
    -kernel ./vmlinuz-5.0.5-generic \
    -L pc-bios/ \
    -append "console=ttyS0 root=/dev/ram oops=panic panic=1" \
    -monitor /dev/null \
    -device vn,id=vda
