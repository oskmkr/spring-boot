@echo off
REM  The contents of this file are subject to the Mozilla Public License
set RABBITMQ_NODE_PORT=%2
set RABBITMQ_NODENAME=%1
set RABBITMQ_MNESIA_DIR=mnesia-%1
set RABBITMQ_LOG_BASE=log-%1
set RABBITMQ_SERVER_START_ARGS=-rabbitmq_management listener [{port,%3}]
rabbitmq-server.bat -detached