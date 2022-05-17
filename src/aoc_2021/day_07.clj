(ns aoc-2021.day-07
  (:require [aoc-2021.common :refer [parse-input]]))

(defn- format-data
  [filename]
  (->> (parse-input filename)
       (re-seq #"\d+")
       (mapv parse-long)))

(defn- triangulate-num
  [n]
  (* n (/ (inc n) 2)))

(defn- total-fuel-cost
  [x data cost]
  (->> (map #(cost (abs (- % x))) data)
       (apply +)))

(defn- optimum-fuel-cost
  [filename cost]
  (let [data (format-data filename)]
    (->> (range (apply min data) (inc (apply max data)))
         (mapv #(total-fuel-cost % data cost))
         (apply min))))

;; Part-1
(defn part-1
  [filename]
  (optimum-fuel-cost filename identity))

;; Part-2
(defn part-2
  [filename]
  (optimum-fuel-cost filename triangulate-num))
