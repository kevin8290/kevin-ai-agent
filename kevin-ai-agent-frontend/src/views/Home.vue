<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const apps = [
  {
    id: 'love-app',
    title: 'AI 恋爱大师',
    description: '恋爱心理专家，帮你分析单身、恋爱、已婚各阶段的情感问题。',
    route: '/love-app',
    emoji: '💕',
    color: '#ec4899',
    gradient: 'linear-gradient(135deg, #fdf2f8 0%, #fce7f3 100%)',
  },
  {
    id: 'manus',
    title: 'AI 超级智能体',
    description: '具备自主规划与工具调用能力，可完成复杂任务的多步骤智能体。',
    route: '/manus',
    emoji: '🤖',
    color: '#6366f1',
    gradient: 'linear-gradient(135deg, #eef2ff 0%, #e0e7ff 100%)',
  },
]

function openApp(route) {
  router.push(route)
}
</script>

<template>
  <div class="home">
    <div class="bg-orb bg-orb-left" />
    <div class="bg-orb bg-orb-right" />

    <div class="home-inner">
      <header class="hero">
        <p class="hero-badge">Kevin AI Platform</p>
        <h1>智能应用中心</h1>
        <p class="hero-desc">选择一个 AI 应用，开启高效、自然的人机协作体验</p>
      </header>

      <section class="app-grid">
        <article
          v-for="app in apps"
          :key="app.id"
          class="app-card"
          @click="openApp(app.route)"
        >
          <div class="app-card-top" :style="{ background: app.gradient }">
            <div class="app-icon" :style="{ color: app.color }">
              {{ app.emoji }}
            </div>
          </div>
          <div class="app-card-body">
            <h2>{{ app.title }}</h2>
            <p>{{ app.description }}</p>
            <span class="enter-link" :style="{ color: app.color }">
              进入应用
              <span class="arrow">→</span>
            </span>
          </div>
        </article>
      </section>
    </div>
  </div>
</template>

<style scoped>
.home {
  position: relative;
  min-height: 100vh;
  overflow: hidden;
  background:
    radial-gradient(circle at top, rgba(99, 102, 241, 0.08), transparent 42%),
    linear-gradient(180deg, #f8fafc 0%, #eef2ff 48%, #f8fafc 100%);
}

.bg-orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  pointer-events: none;
}

.bg-orb-left {
  width: 420px;
  height: 420px;
  top: -120px;
  left: -120px;
  background: rgba(236, 72, 153, 0.18);
}

.bg-orb-right {
  width: 520px;
  height: 520px;
  top: 120px;
  right: -180px;
  background: rgba(99, 102, 241, 0.16);
}

.home-inner {
  position: relative;
  z-index: 1;
  max-width: var(--content-width);
  margin: 0 auto;
  padding: 72px 32px 96px;
}

.hero {
  text-align: center;
  margin-bottom: 64px;
}

.hero-badge {
  display: inline-flex;
  align-items: center;
  margin: 0 0 20px;
  padding: 8px 18px;
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.72);
  border: 1px solid var(--color-border);
  color: var(--color-primary);
  font-size: 13px;
  font-weight: 600;
  letter-spacing: 0.08em;
  text-transform: uppercase;
}

.hero h1 {
  margin: 0 0 16px;
  font-size: clamp(40px, 6vw, 56px);
  line-height: 1.15;
  font-weight: 700;
  letter-spacing: -0.03em;
  color: var(--color-text);
}

.hero-desc {
  margin: 0 auto;
  max-width: 640px;
  font-size: 18px;
  line-height: 1.8;
  color: var(--color-text-muted);
}

.app-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 32px;
}

.app-card {
  background: rgba(255, 255, 255, 0.88);
  border-radius: var(--radius-xl);
  overflow: hidden;
  cursor: pointer;
  border: 1px solid rgba(255, 255, 255, 0.8);
  box-shadow: var(--shadow-sm);
  backdrop-filter: blur(12px);
  transition: transform 0.28s ease, box-shadow 0.28s ease;
}

.app-card:hover {
  transform: translateY(-8px);
  box-shadow: var(--shadow-md);
}

.app-card-top {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 180px;
}

.app-icon {
  width: 88px;
  height: 88px;
  border-radius: 28px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 42px;
  background: rgba(255, 255, 255, 0.82);
  box-shadow: 0 12px 32px rgba(15, 23, 42, 0.08);
}

.app-card-body {
  padding: 32px 36px 40px;
}

.app-card-body h2 {
  margin: 0 0 12px;
  font-size: 28px;
  font-weight: 700;
  letter-spacing: -0.02em;
}

.app-card-body p {
  margin: 0 0 28px;
  min-height: 84px;
  font-size: 16px;
  line-height: 1.8;
  color: var(--color-text-muted);
}

.enter-link {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  font-size: 15px;
  font-weight: 600;
}

.arrow {
  transition: transform 0.2s ease;
}

.app-card:hover .arrow {
  transform: translateX(4px);
}
</style>
