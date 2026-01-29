FROM openliberty/open-liberty:full-java8-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

USER root
# make is for snapit
# porcps-ng is for vmstat, ps, top (in linperf.sh)
# net-tools is for netstat (in inperf.sh)
# gdb is for gcore
RUN dnf install -y make procps-ng net-tools gdb
COPY --chown=1001:0 config/server.xml /config/
COPY --chown=1001:0 apps/pdprof-resource-adapter.rar /config/
COPY --chown=1001:0 apps/resource-ear.ear /config/apps/

USER 1001
