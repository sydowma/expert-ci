# model

Build 是一次CI构建执行
build {
  repo_id
  name
}

Stage 是每次执行的任务，基于某个镜像做的
stage {
  repo_id
  build_id
}

Step 是每个执行的步骤
step {
  stage_id
  step_number
}