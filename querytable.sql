--
-- Database: `testing`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sample`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `NoInduk` varchar(10) NOT NULL,
  `Nama` varchar(250) NOT NULL,
  `TahunMasuk` varchar(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_sample`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_sample`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Database: `testing`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sample`
--

CREATE TABLE `dosen` (
  `id` int(11) NOT NULL,
  `NoInduk` varchar(10) NOT NULL,
  `Nama` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_sample`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_sample`
--
ALTER TABLE `dosen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

  --
-- Database: `testing`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sample`
--

CREATE TABLE `mata_kuliah` (
  `id` int(11) NOT NULL,
  `KodeMatkul` varchar(10) NOT NULL,
  `Nama` varchar(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_sample`
--
ALTER TABLE `mata_kuliah`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_sample`
--
ALTER TABLE `mata_kuliah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;