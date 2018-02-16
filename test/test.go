package main

import (
	"fmt"
	"math/rand"
	"time"
)

type Grid map[Position][]Test

func (g Grid) remove(t Test, p Position) {
	newG := []Test{}
	flg := true
	for _, v := range g[p] {
		if v == t && flg {
			flg = false
			continue
		} else {
			newG = append(newG, v)
		}
	}
	g[p] = newG
}

func (g Grid) add(t Test, p Position) {
	if g[p] == nil {
		g[p] = make([]Test, 0)
	}
	g[p] = append(g[p], t)
}

type Position struct {
	x, y, z int
}

type Test struct {
	p Position
}

func (t *Test) move() {
	t.p.x += rand.Intn(2) - 1
	t.p.y += rand.Intn(2) - 1
	t.p.z += rand.Intn(2) - 1
}

// 2198ms
func test1(ps []Position) {
	g := Grid{}
	ts := make([]Test, 0)
	for _, p := range ps {
		t := Test{p: p}
		ts = append(ts, t)
		g.add(t, p)
	}
	for _, t := range ts {
		g.remove(t, t.p) // 322ms
		t.move()         // 755ms
		g.add(t, t.p)    // 873ms
	}
}

func main() {
	rand.Seed(time.Now().UnixNano())
	ps := make([]Position, 0)

	for i := 0; i < 1000000; i++ {
		p := Position{x: rand.Intn(300), y: rand.Intn(300), z: rand.Intn(300)}
		ps = append(ps, p)
	}

	start := time.Now()
	test1(ps)
	fmt.Println(time.Now().Sub(start).Nanoseconds()/1000000, "ms")
}
