#!/bin/sh

#cp -r /home/derek/streetpin-spring-git/shine/sp-mob-web/src/main/webapp/WEB-INF/views/admin/boardlocations/* /home/derek/apps/apache-tomcat-7.0.29/webapps/shine/WEB-INF/views/admin/boardlocations

rsync -av --include *.jsp /home/derek/streetpin-spring-git/shine/sp-mob-web/src/main/webapp/WEB-INF/views/ /home/derek/apps/apache-tomcat-7.0.29/webapps/shine/WEB-INF/views

echo 'enter to continue'

read in
