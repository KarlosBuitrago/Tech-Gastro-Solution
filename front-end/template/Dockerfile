FROM php:8.3-apache  # O php:8.2-apache si usas versión anterior
WORKDIR /var/www/html
COPY . .
RUN if [ -f composer.json ]; then composer install --no-dev --optimize-autoloader; fi  # Si usas Composer
EXPOSE 80
CMD ["apache2-foreground"]
