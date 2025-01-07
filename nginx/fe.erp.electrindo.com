server {
  gzip off;

  listen 443 ssl default_server;
  server_name erp.electrindo.com;

  root /var/www/erp-project/dist;
  index index.html;

  ssl_certificate /etc/letsencrypt/live/erp.electrindoid.com/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/erp.electrindoid.com/privkey.pem;
  include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
  ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot

  location / {
    try_files $uri uri/ /index.html;
  }

  location /static/ {
    alias /var/www/erp-project/static;
    expires max;
    add_header Cache-Control public;
  }
}