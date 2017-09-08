FROM postgres:9.6.2-alpine

USER postgres
RUN    /etc/init.d/postgresql start &&\
    psql --command "CREATE USER test WITH SUPERUSER PASSWORD '';" &&\
    createdb -O test test

RUN echo "host all  all    0.0.0.0/0  md5" >> /etc/postgresql/9.6.2/main/pg_hba.conf

RUN echo "listen_addresses='*'" >> /etc/postgresql/9.6.2/main/postgresql.conf

EXPOSE 5432

VOLUME  ["/etc/postgresql", "/var/log/postgresql", "/var/lib/postgresql"]

CMD ["/usr/lib/postgresql/9.6.2/bin/postgres", "-D", "/var/lib/postgresql/9.6.2/main", "-c", "config_file=/etc/postgresql/9.6.2/main/postgresql.conf"]