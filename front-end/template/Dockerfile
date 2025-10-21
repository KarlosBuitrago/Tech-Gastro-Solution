FROM php:8.1-apache
WORKDIR /var/www/html

# Copy template site into Apache document root
COPY . /var/www/html/

# Install utilities (envsubst) and common extensions (if needed)
RUN apt-get update \
  && apt-get install -y --no-install-recommends gettext-base \
  && docker-php-ext-install mysqli pdo pdo_mysql \
  && a2enmod rewrite \
  && rm -rf /var/lib/apt/lists/*

# Copy entrypoint that will inject env vars into PHP files at container start
COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

EXPOSE 80

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
CMD ["apache2-foreground"]
