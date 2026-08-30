# ---- 构建阶段 ----
FROM node:20-alpine AS builder

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci

COPY . .

# 通过 Nginx 同源代理 /api，构建时使用相对路径
ENV VITE_API_BASE_URL=/api
ENV VITE_APP_API_ENV=production
RUN npm run build

# ---- 运行阶段 ----
FROM nginx:1.27-alpine

# 后端服务地址（可在微信云托管控制台覆盖此环境变量）
ENV BACKEND_URL=https://kevin-ai-agent-back-304016-11-1476321615.sh.run.tcloudbase.com

COPY nginx/default.conf.template /etc/nginx/templates/default.conf.template
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

# 官方镜像会自动 envsubst 模板并启动 nginx
CMD ["nginx", "-g", "daemon off;"]
