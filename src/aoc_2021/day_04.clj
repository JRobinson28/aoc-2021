(ns aoc-2021.day-04
  (:require [aoc-2021.common :refer :all]
            [clojure.string :as s]))

(defn- parse-nums-and-boards
  [filename]
  (let [input (parse-input filename)
        [nums & boards] (s/split-lines input)]
    {:nums (->> (s/split nums #",")
                (map parse-int))
     :boards (->> boards
                  (mapcat #(re-seq #"\d+" %))
                  (map #(identity {:marked? false
                                   :value (parse-int %)}))
                  (partition 25))}))

(defn- board-won?
  [board]
  (let [rows (partition 5 board)
        cols (apply map vector rows)]
    (->> (concat rows cols)
         (some #(every? :marked? %))
         some?)))

(defn- mark-val
  [num boards]
  (for [board boards]
    (reduce (fn [checked cell]
              (conj checked (update cell :marked? #(or % (= num (:value cell))))))
            [] board)))

(defn- get-final-val
  [board num]
  (->> board
       (remove :marked?)
       (map :value)
       (apply +)
       (* num)))

;; Part 1
(defn part-1
  [filename]
  (let [{:keys [nums boards]} (parse-nums-and-boards filename)]
    (loop [[num & nums] nums
           boards (mark-val num boards)]
      (let [board-check (map board-won? boards)]
        (if-not (some true? board-check)
          (recur nums
                 (mark-val (first nums) boards))
          (let [board (nth boards (.indexOf board-check true))]
            (get-final-val board num)))))))

(part-1 "day_04")

;; Part 2
(defn part-2
  [filename]
  (let [{:keys [nums boards]} (parse-nums-and-boards filename)]
    (loop [[num & nums] nums
           last-board-index nil
           boards (mark-val num boards)]
      (let [board-check (map board-won? boards)
            marked-boards (mark-val (first nums) boards)
            num-boards-won (count (filter true? board-check))]
        (cond
          (= num-boards-won (dec (count boards)))
          (recur nums
                 (.indexOf board-check false)
                 marked-boards)

          (not-every? true? board-check)
          (recur nums
                 last-board-index
                 marked-boards)

          :else
          (let [board (nth boards last-board-index)]
            (get-final-val board num)))))))

(part-2 "day_04")
