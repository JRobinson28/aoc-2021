(ns aoc-2021.day-04
  (:require [aoc-2021.common :refer [parse-input]]
            [clojure.string :as s]))

(def data (parse-input "day_04"))

(defn parse-boards
  [data]
  (->> data
       rest
       (remove s/blank?)
       (partition 5)
       (map #(s/join #" " %))
       (map #(s/split % #" "))
       (map #(remove s/blank? %))))

(def boards (parse-boards data))
(def numbers (s/split (first data) #","))

(def winning-combos [[0 1 2 3 4]
                     [5 6 7 8 9]
                     [10 11 12 13 14]
                     [15 16 17 18 19]
                     [20 21 22 23 24]
                     [0 5 10 15 20]
                     [1 6 11 16 21]
                     [2 7 12 17 22]
                     [3 8 13 18 23]
                     [4 9 14 19 24]])

(defn board-won?
  [board]
  (->> (for [combo winning-combos]
         (map #(nth board %) combo))
       (filter #(every? nil? %))
       not-empty
       boolean))

(defn remove-number
  [num boards]
  (for [board boards]
    (reduce (fn [checked val]
              (if (= num val)
                (conj checked nil)
                (conj checked val)))
            [] board)))

(defn get-final-val
  [board-index boards num-index numbers]
  (let [vals (->> (nth boards board-index)
                  (cons (nth numbers (- num-index 1)))
                  (remove nil?)
                  (map #(Integer/parseInt %)))]
    (* (first vals) (apply + (rest vals)))))

(defn winning-board-val []
  (loop [num-index 0
         boards boards]
    (let [board-check (map board-won? boards)
          num (nth numbers num-index)]
      (if-not (some true? board-check)
        (recur (inc num-index)
               (remove-number num boards))
        (-> (.indexOf board-check true)
            (get-final-val boards num-index numbers))))))

;; Part 1
(winning-board-val)

(defn last-winning-board-val []
  (loop [num-index 0
         last-board-index nil
         boards boards]
    (let [board-check (map board-won? boards)
          num (nth numbers num-index)]
      (cond
        (= (count (filter true? board-check))
           (count (rest board-check)))
        (recur (inc num-index)
               (.indexOf board-check false)
               (remove-number num boards))

        (not-every? true? board-check)
        (recur (inc num-index)
               last-board-index
               (remove-number num boards))

        :else
        (get-final-val last-board-index boards num-index numbers)))))

;; Part 2
(last-winning-board-val)
