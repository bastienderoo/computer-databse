FROM jenkins:2.46.2
MAINTAINER bastien

USER root
RUN mkdir /var/log/jenkins
RUN chown -R  jenkins:jenkins /var/log/jenkins
RUN mkdir /cdb
RUN chown -R  jenkins:jenkins /cdb
RUN apt-get update \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN groupadd -g 999 docker && usermod -a -G docker jenkins

USER jenkins

#sudo cp -r * /cdb
#sudo rm -rf ./*
#docker start -i test_maven
#docker start -i prod_maven

#sudo cp /cdb/target/ComputerDatabase.war /cdb_war



#sudo rm -rf /cdb/*

