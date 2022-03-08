(ns aoc-2021.day-05
  (:require [aoc-2021.common :refer :all]
            [clojure.string :as s]))

(defn- format-data
  [filename]
  (->> (parse-input filename)
       (re-seq #"\d+")
       (map parse-int)
       (partition 2)
       (partition 2)))

(defn- points-range
  [start stop step]
  (range start (+ stop step) step))

(defn- direction
  [p1 p2]
  (if (>= p1 p2) -1 1))

(defn- diagonal?
  [[[x1 y1] [x2 y2]]]
  (and (not= x1 x2) (not= y1 y2)))

(defn- points-covered
  [[[x1 y1] [x2 y2] :as coords]]
  (let [xs (points-range x1 x2 (direction x1 x2))
        ys (points-range y1 y2 (direction y1 y2))]
    (if (diagonal? coords)
      (map vector xs ys)
      (for [x xs y ys] [x y]))))

(defn- solve
  [filename keep-line?]
  (->> (format-data filename)
       (filter keep-line?)
       (mapcat points-covered)
       frequencies
       (filter #(> (val %) 1))
       count))

;; Part 1
(defn part-1
  [filename]
  (solve filename (complement diagonal?)))

;; Part 2
(defn part-2
  [filename]
  (solve filename identity))
