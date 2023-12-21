package com.mesum.coinlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mesum.coinlist.databinding.FragmentCoinListFragemntBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CoinListFragemnt : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _biding : FragmentCoinListFragemntBinding? = null
    private val binding get() =  _biding!!

    private val viewModel: CoinListViewModel by viewModels()
    private val coinListAdapter: CoinListAdapter = CoinListAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _biding = FragmentCoinListFragemntBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{ state ->
                    when{
                        state.error.isNotBlank() -> {
                            binding.txErr.visibility = View.VISIBLE
                            binding.txErr.text = state.error
                        }
                        state.isLoading ->{
                            binding.cpIndicator.visibility = View.VISIBLE
                        }
                        state.coins.isNotEmpty() ->{
                            binding.cpIndicator.visibility = View.GONE
                            binding.txErr.visibility = View.GONE
                            binding.rvCoinList.layoutManager = LinearLayoutManager(context)
                            coinListAdapter.submitList(state.coins)
                            binding.rvCoinList.adapter = coinListAdapter

                        }
                    }

                }
            }
        }
    }


}