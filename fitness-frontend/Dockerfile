# ---------- BUILD STAGE ----------
FROM node:18 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# ---------- SERVE WITH NGINX ----------
FROM nginx:latest
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80
