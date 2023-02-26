
#run shell

docker build . -t escape_langlang_mountain

docker run -d -p "0.0.0.0:9999:9999" -h "escape_langlang_mountain" --name="escape_langlang_mountain" escape_langlang_mountain

nc 0.0.0.0 9999

