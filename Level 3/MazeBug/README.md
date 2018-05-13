# MazeBug

- Method
  - MazeBug
  - act
  - move
  - reachRedRock
  - getUnvisitedLocation
  - getValidAdjacentLocations
  - showPath
  - selectDirection
  - selectLocation

- Run 点击运行后，调用 act 方法
  - 判断是否到达终点
    - 到达终点则显示路径以及步数
    - 未到达终点寻找下一位置
      - 查找周围四个方向为走过的可走的位置
        - 如果无可走位置，则回到上一个位置
        - 如果有可走位置
          - 可走位置为一个则直接作为下一个位置
          - 可走位置大于一个时查看以往所有走过的方向，取已走方向更多的位置当作下一个位置
      - 步数加一
